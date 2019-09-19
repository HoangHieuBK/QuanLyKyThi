package global.testingsystem.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface UploadFileService {
	
      String saveFile(MultipartFile file);
      
      Resource findFileByName(String nameFile);
      
      String saveFileVer(MultipartFile file,String pathToSave);
      
      // update 22/5
      String getPathFile(MultipartFile file);

  	 boolean checkExtension(MultipartFile file);

  	 public Resource loadFile(String filename);
  	 
     String uploadFile(MultipartFile file);
}
