package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

/**
 *
 * @author Alves
 */
public class AnalysePathPropertiesMain {

    public static void main(String[] args) throws IOException {
        final Path path1 = Path.of("G:/users/joe/docs/some.txt");
        final Path path2 = Path.of("G:/users/joe/docs/some.txt");
        final Path path3 = Path.of("G:/users/joe/pics/s.txt");
        
        System.out.println("isDirectory: " + Files.isDirectory(path1));
        System.out.println("isExecutable: " + Files.isExecutable(path1));
        System.out.println("isHidden: " + Files.isHidden(path1));
        System.out.println("isReadable: " + Files.isReadable(path1));
        System.out.println("isWritable: " + Files.isWritable(path1));
        System.out.println("isRegularFile: " + Files.isRegularFile(path1));
        System.out.println("isSymbolicLink: " + Files.isSymbolicLink(path1));
        System.out.println("isSameFile: " + Files.isSameFile(path1, path2));
        System.out.println("isSameFile: " + Files.isSameFile(path1, path3));
        System.out.println("probeContentType: " + Files.probeContentType(path1));
        
        try {
            final PosixFileAttributes posixAttr = Files.readAttributes(path1, PosixFileAttributes.class);
            final long size = posixAttr.size();
            System.out.println(size);

            final FileTime creationTime = posixAttr.creationTime();
            final FileTime lastModifiedTime = posixAttr.lastModifiedTime();
            final FileTime lastAccessTime = posixAttr.lastAccessTime();

            System.out.println(creationTime);
            System.out.println(lastModifiedTime);
            System.out.println(lastAccessTime);

            final UserPrincipal user = posixAttr.owner();
            final GroupPrincipal group = posixAttr.group();
            final Set<PosixFilePermission> permissions = posixAttr.permissions();
            final String permissionsStr = PosixFilePermissions.toString(permissions);

            System.out.println(user);
            System.out.println(group);
            System.out.println(permissions);
            System.out.println(permissionsStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
