package cc.qwer.controller;

import cc.qwer.config.YamlProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpringBootTest
class FileDownloadControllerTest {

    @Autowired
    YamlProperties yamlProperties;

    @Test
    void download() throws IOException {
        String FILE_URL = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=" + yamlProperties.getKey();
        URL url = new URL(FILE_URL);
        ReadableByteChannel channel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("test.zip");
        fileOutputStream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
    }

    @Test
    void unzip() throws IOException {
        Path source = Paths.get("/Users/seunghwanwon/Library/Mobile Documents/com~apple~CloudDocs/programing/java/toyProject/qwer/test.zip");
        Path target = Paths.get("/Users/seunghwanwon/Library/Mobile Documents/com~apple~CloudDocs/programing/java/toyProject/qwer/src/main/resources/");

        try {
            unzipFolder(source, target);
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void unzipFolder(Path source, Path target) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {
            ZipEntry zipEntry = zis.getNextEntry();

            while(zipEntry != null) {
                boolean isDirectory = false;

                if (zipEntry.getName().endsWith(File.separator)) {
                    isDirectory = true;
                }

                Path newPath = zipSlipProtect(zipEntry, target);

                if (isDirectory) {
                    Files.createDirectories(newPath);
                } else {
                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent());
                        }
                    }

                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }

                zipEntry = zis.getNextEntry();
            }
            zis.close();
        }
    }

    private Path zipSlipProtect(ZipEntry zipEntry, Path target) throws IOException {

        Path targetDirResolved = target.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(target)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }
        return normalizePath;
    }

}