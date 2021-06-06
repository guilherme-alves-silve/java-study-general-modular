package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alves
 */
public class FileAttributeViewMain {

    private static final Logger LOGGER = Logger.getLogger(FileAttributeViewMain.class.getName());
    
    public static void main(String[] args) throws IOException {
        
        if (!ArgsUtils.requireMinSize(args, 1)) {
            return;
        }
       
        final Path path = Paths.get(args[0]);
        
        final BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        final BasicFileAttributes basic = view.readAttributes();
        
        System.out.println("creationTime: " + basic.creationTime());
        System.out.println("fileKey: " + basic.fileKey());
        System.out.println("isDirectory: " + basic.isDirectory());
        System.out.println("isOther: " + basic.isOther());
        System.out.println("isRegularFile: " + basic.isRegularFile());
        System.out.println("isSymbolicLink: " + basic.isSymbolicLink());
        System.out.println("lastAccessTime: " + basic.lastAccessTime());
        System.out.println("lastModifiedTime: " + basic.lastModifiedTime());
        System.out.println("size: " + basic.size());
        
        final FileTime lastModifiedTime = FileTime.from(Instant.now());
        final FileTime lastAccessTime = FileTime.from(2, TimeUnit.SECONDS);
        final FileTime createTime = FileTime.fromMillis(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
        
        view.setTimes(lastModifiedTime, lastAccessTime, createTime);
        
        LOGGER.info("********************************************");
        
        try {
            final PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            if (posixView != null) {
                final PosixFileAttributes posixAttributes = posixView.readAttributes();
                System.out.println("owner: " + posixAttributes.owner());
                System.out.println("group: " + posixAttributes.group());
            } else {
                LOGGER.warning("PosixFileAttributeView is not supported!");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(), ex);
        }
        
         LOGGER.info("********************************************");
        
        try {
            final DosFileAttributeView dosView  = Files.getFileAttributeView(path, DosFileAttributeView.class);
            if (dosView != null) {
                final DosFileAttributes dosAttributes = dosView.readAttributes();
                System.out.println("isArchive: " + dosAttributes.isArchive());
                System.out.println("isHidden: " + dosAttributes.isHidden());
                System.out.println("isReadOnly: " + dosAttributes.isReadOnly());
                System.out.println("isSystem: " + dosAttributes.isSystem());
            } else {
                LOGGER.warning("DosFileAttributeView is not supported!");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(), ex);
        }
    }
}
