package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pauloho on 29/08/18.
 */

@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileService service;

    @RequestMapping(value = "/upload", headers = "content-type=multipart/*",  method = RequestMethod.POST)
    public String uploadImage(@RequestParam MultipartFile image){
        return service.upload("images", image);
    }

    @GetMapping("/{filename:.*}")
    public Resource downloadFile(@PathVariable("filename") String filename) {
        return  service.loadFile(filename);
    }

}
