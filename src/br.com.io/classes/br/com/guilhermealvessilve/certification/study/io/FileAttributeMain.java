package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

/**
 *
 * @author Alves
 */
public class FileAttributeMain {

    public static void main(String[] args) throws IOException {
        
        ArgsUtils.requireMinSize(args, 1);
        
        final Path path = Paths.get(args[0]);
        
        final BasicFileAttributes basic = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("BasicFileAttributes");
        System.out.println("creationTime: " + basic.creationTime());
        System.out.println("lastModifiedTime: " + basic.lastModifiedTime());
        System.out.println("lastAccessTime: " + basic.lastAccessTime());
        System.out.println("fileKey: " + basic.fileKey());
        System.out.println("isDirectory: " + basic.isDirectory());
        System.out.println("isOther: " + basic.isOther());
        System.out.println("isRegularFile: " + basic.isRegularFile());
        System.out.println("isSymbolicLink: " + basic.isSymbolicLink());
        System.out.println("size: " + basic.size());
        System.out.println();
        
        try {
            final DosFileAttributes dos = Files.readAttributes(path, DosFileAttributes.class);
            System.out.println("DosFileAttributes");
            System.out.println("isArchive: " + dos.isArchive());
            System.out.println("isSystem: " + dos.isSystem());
            System.out.println("isHidden: " + dos.isHidden());
            System.out.println("isReadOnly: " + dos.isReadOnly());
        } catch (UnsupportedOperationException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        System.out.println();
        
        try {
            final PosixFileAttributes posix = Files.readAttributes(path, PosixFileAttributes.class);
            System.out.println("PosixFileAttributes");
            System.out.println("group: " + posix.group());
            System.out.println("owner: " + posix.owner());
            System.out.println("permissions: " + posix.permissions());
        } catch (UnsupportedOperationException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
