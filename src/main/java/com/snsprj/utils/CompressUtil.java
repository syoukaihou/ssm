package com.snsprj.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 压缩/解压缩工具类
 * @author  xiaohb
 */
public class CompressUtil {

    private static final Logger logger = LoggerFactory.getLogger(CompressUtil.class);

    private static final int BUFFER_SIZE = 1024;

    /**
     *
     * 解压zip包
     *
     * @param inputStream
     * @param targetDirPath
     * @return
     * @throws IOException
     */
    public static List<String> unZip(InputStream inputStream,String targetDirPath)
      throws IOException {

        // 解压zip包
        ZipArchiveInputStream zais = new ZipArchiveInputStream(inputStream);

        ArchiveEntry archiveEntry = null;
        List<String> filePathList = new ArrayList<>();

        // 创建临时文件夹
        File rootDirectory = new File(targetDirPath);
        boolean isRootDirectoryExist = rootDirectory.mkdirs();
        logger.info("====> create root dir is success? " + isRootDirectoryExist);
        logger.info("====> root dir path is " + targetDirPath);

        while ((archiveEntry = zais.getNextEntry()) != null) {

            //获取文件名
            String archiveEntryFileName = archiveEntry.getName();
            logger.info("====> archiveEntryFileName is " + archiveEntryFileName);

            if (archiveEntry.isDirectory()) {
                File directory = new File(targetDirPath, archiveEntryFileName);
                boolean isDirExist = directory.exists();
                if (!isDirExist){
                    boolean isExist = directory.mkdirs();
                    logger.info("====> create dir is success? " + isExist);
                    logger.info("====> dir path is " + targetDirPath);
                }
            } else {
                OutputStream os = null;
                try {
                    os = new BufferedOutputStream(
                      new FileOutputStream(new File(targetDirPath, archiveEntryFileName)),
                      BUFFER_SIZE);
                    IOUtils.copy(zais, os);
                    filePathList.add(targetDirPath + archiveEntryFileName);
                } finally {
                    IOUtils.closeQuietly(os);
                }
            }
        }
        return filePathList;
    }
}
