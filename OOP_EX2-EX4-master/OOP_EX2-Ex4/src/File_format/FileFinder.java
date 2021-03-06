	package File_format;

	import java.io.IOException;
	import java.nio.file.FileSystems;
	import java.nio.file.FileVisitResult;
	import java.nio.file.Path;
	import java.nio.file.PathMatcher;
	import java.nio.file.SimpleFileVisitor;
	import java.nio.file.attribute.BasicFileAttributes;
	import java.util.ArrayList;

	public class FileFinder extends SimpleFileVisitor<Path>{
		
		private PathMatcher matcher;
		public ArrayList<Path> foundpaths = new ArrayList<>();
		
		public FileFinder(String pattern) {
			matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
		}
		
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
			
			Path name = file.getFileName();
			System.out.println("Examining " + name);
			if (matcher.matches(name)) {
				foundpaths.add(file);
			}
			
			return FileVisitResult.CONTINUE;
			
		}

}
