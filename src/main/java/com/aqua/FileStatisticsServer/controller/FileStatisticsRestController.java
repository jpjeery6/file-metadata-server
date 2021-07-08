package com.aqua.FileStatisticsServer.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aqua.FileStatisticsServer.Utils;
import com.aqua.FileStatisticsServer.model.FileInfo;
import com.aqua.FileStatisticsServer.model.FileInfoComparator;
import com.aqua.FileStatisticsServer.model.FileOrdering;
import com.aqua.FileStatisticsServer.model.FileServerStats;
import com.aqua.FileStatisticsServer.model.FileStats;
import com.aqua.FileStatisticsServer.repositories.FileOrderingRepository;
import com.aqua.FileStatisticsServer.repositories.FileServerStatsRepository;
import com.aqua.FileStatisticsServer.repositories.FileStatsRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileStatisticsRestController {

    private final FileStatsRepository statsRepository;
    private final FileServerStatsRepository serverStatsRepository;
    private final FileOrderingRepository orderingRepository;

    public FileStatisticsRestController(FileStatsRepository statsRepository,
            FileServerStatsRepository serverStatsRepository, FileOrderingRepository orderingRepository) {
        this.statsRepository = statsRepository;
        this.serverStatsRepository = serverStatsRepository;
        this.orderingRepository = orderingRepository;
    }

    @GetMapping("/")
    String defaultMap() {
        return "Welcome to Aqua Test";
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping("/uploadfilestats")
    String uploadStats(@RequestBody FileStats fileStats) {

        try {
            int fileOrderNum;

            Optional<FileOrdering> fileOrderingOPt = orderingRepository.findById(fileStats.getClient_id());
            if (fileOrderingOPt.isEmpty()) {
                // orderingRepository.save(new FileOrdering(fileStats.getClient_id(), 1));
                fileOrderNum = 1;
            } else {
                FileOrdering fileOrdering = fileOrderingOPt.get();
                fileOrderNum = fileOrdering.getNum();
            }

            // System.out.println("Initial fileOrderNum=" + fileOrderNum);
            List<FileInfo> fileInfoListExisting;
            List<FileInfo> fileInfoList = fileStats.getFilesInfo();

            Optional<FileStats> fileStatsExisting = statsRepository.findById(fileStats.getClient_id());
            if (fileStatsExisting.isEmpty()) {

                for (FileInfo fileInfo : fileInfoList) {
                    fileInfo.setFilenum(fileOrderNum);
                    fileOrderNum++;
                }
            } else {
                System.out.println("Intermediate fileOrderNum=" + fileOrderNum);
                fileInfoListExisting = fileStatsExisting.get().getFilesInfo();
                for (FileInfo fileInfo : fileInfoList) {
                    if (fileInfoListExisting.contains(fileInfo)) {
                        // System.out.println("Inside contains");
                        fileInfo.setFilenum(
                                fileInfoListExisting.get(fileInfoListExisting.indexOf(fileInfo)).getFilenum());
                    } else {
                        // System.out.println("Not going Inside contains");
                        fileInfo.setFilenum(fileOrderNum);
                        fileOrderNum++;
                    }
                }
            }

            // System.out.println("fileInfoList=" + fileStats.getFilesInfo().toString());
            // System.out.println("Final fileOrderNum=" + fileOrderNum);

            statsRepository.save(fileStats);
            orderingRepository.save(new FileOrdering(fileStats.getClient_id(), fileOrderNum));

            return "Success";

        } catch (DataAccessException sqlex) {
            return "Success"; // that means already updated
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @GetMapping("/getfilestats")
    List<FileStats> getAllStats() {
        List<FileStats> actualList = new ArrayList<FileStats>();
        statsRepository.findAll().forEach(actualList::add);
        return actualList;
    }

    @GetMapping("/getservercomputedstats")
    List<FileServerStats> getComputedServerStats() {

        List<FileServerStats> fileServerStatsList = new ArrayList<FileServerStats>();
        List<FileStats> fileStats = new ArrayList<FileStats>();
        statsRepository.findAll().forEach(fileStats::add);

        for (FileStats fileStat : fileStats) {

            Long maxFileSize = -1l;
            Long sumFileSize = 0l;
            String maxFile = "";
            Map<String, Integer> fileExtsAndFreq = new HashMap<>();
            StringBuffer latestFiles = new StringBuffer();
            StringBuffer fileExts = new StringBuffer();

            // sort list first to get 10 latest files
            List<FileInfo> fileInfos = fileStat.getFilesInfo();
            System.out.println(fileInfos);
            Collections.sort(fileInfos, new FileInfoComparator());

            for (FileInfo fileInfo : fileInfos) {
                int i = 10;

                // Long maxFileSize;
                // String maxFile;
                if (fileInfo.getSize() > maxFileSize) {
                    maxFileSize = fileInfo.getSize();
                    maxFile = fileInfo.getFile();
                }

                // Double avgFileSize;
                sumFileSize += fileInfo.getSize();

                String fileExt = Utils.getFileExtFromFile(fileInfo.getFile());
                if (fileExtsAndFreq.containsKey(fileExt)) {
                    fileExtsAndFreq.put(fileExt, fileExtsAndFreq.get(fileExt) + 1);
                } else {
                    fileExtsAndFreq.put(fileExt, 1);
                    fileExts.append(fileExt + ",");
                }

                if (i > 0) {
                    latestFiles.append(fileInfo.getFile() + ",");
                }
            }

            // compute here

            Map.Entry<String, Integer> val = Utils.getMaxEntryInMapBasedOnValue(fileExtsAndFreq);
            String mostFreqFileExtRes = val.getKey();
            int freqRes = val.getValue();

            // all computations ready
            FileServerStats fileServerStats = new FileServerStats(fileStat.getClient_id(), fileStat.getFilesCount(),
                    maxFileSize, maxFile, Double.valueOf(sumFileSize / fileStat.getFilesCount()), fileExts.toString(),
                    mostFreqFileExtRes, freqRes, latestFiles.toString());

            fileServerStatsList.add(fileServerStats);
            serverStatsRepository.save(fileServerStats);

        }

        return fileServerStatsList;

    }

}
