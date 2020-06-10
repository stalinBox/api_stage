package ec.gob.mag.stageRenagro.util;

import java.io.File;

import org.springframework.stereotype.Service;

@Service("deleteImagesTmp")
public class DeleteImagesTmp {

	public boolean deleteImagesLocalTmp(String pathImageLocal) {
		File file = new File(pathImageLocal);
		if (file.delete())
			return true;
		else
			return false;
	}
}
