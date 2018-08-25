package br.edu.unitri.posjava.tcc.med4you.service;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pauloho on 25/08/18.
 */
@Service
public class FileService {



/*    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Event evento, Model model, @RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String path = servletContext.getRealPath("/") + "resources/imagens/" + evento.getName() + ".png";
            FileUtil.saveFile(path, file);
        }
        service.salvar(evento);
        return "redirect:/evento/formulario";
    }*/

    @Autowired
    private ServletContext context;


    private DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");

    public String inserImagemDiretorio(String diretory, FileItem item) throws Exception{

        final String PATH_ARQUIVOS  = context.getRealPath("/resources/uploads/") + diretory + "/";
        final String PATH_ABSOLUTO  = PATH_ARQUIVOS;

        try {


            File diretorio  = new File(PATH_ABSOLUTO);

            // NESSE IF VC PA PERGUNTANDO SE EXISTE UM DIRETÓRIO, CASO NÃO IRÁ CRIAR
            // OU SEJA, SE FOR A PRIMEIRA IMAGEM DO DIA
            // ELE CRIARÁ O DIRETÓRIO <DIR_DATA_ATUAL> EX: 31-07-214
            if(!diretorio.exists())
                diretorio.mkdir();

            String fileName = item.getName();
            String arq[] = fileName.split("\\\\");
            for (int i = 0; i < arq.length; i++) {
                fileName = arq[i];
            }

            File file = new File(diretorio,fileName);
            FileOutputStream out = new FileOutputStream(file);
            InputStream in = item.getInputStream();

            // Imagens de até 2 megas !!
            byte[] buffer = new byte[1024 * 2];
            int nLidos;
            while ((nLidos = in.read(buffer)) >= 0) {
                out.write(buffer, 0, nLidos);
            }

            out.flush();
            out.close();


            // NO FINAL ELE TE RETORNA O CAMINHO DA PASTA ONDE VC SALVOU A IMAGEN
            // ESSA STRING VC PODE ARMAZENAR NA TABELA DO PRODUTO
            // NO CAMPO : CAMINHO_FOTO
            return "/" + PATH_ABSOLUTO + "/" +format.format(Calendar.getInstance().getTime())+item.getName();

        } catch (Exception e) {
            throw new Exception("Erro ao carregar imagem para o diretorio !!\n "
                    + "Error : "      + e.getMessage()
                    + "\nCausa : " + e.getCause());
        }

    }

}
