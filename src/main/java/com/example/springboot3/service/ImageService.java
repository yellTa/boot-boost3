package com.example.springboot3.service;

import static com.example.springboot3.Const.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.example.springboot3.Dao.FileDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final FileDao fileDao;

	public Path getFilePath(int fileId) {
		String filePath = fileDao.findFilePath(fileId);
		return Paths.get(IMAGE_PATH + filePath);
	}
}
