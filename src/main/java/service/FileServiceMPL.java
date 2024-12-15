package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Manga;
import model.Usuario;
import org.jboss.logging.Logger;
import repository.MangaRepository;
import repository.UsuarioRepository;
import resouce.MangaResouce;
import validation.ValidationException;

@ApplicationScoped
public class FileServiceMPL implements FileService {

    private static final Logger LOG = Logger.getLogger(FileServiceMPL.class);

    // ex. /user/janio/quarkus/images/usuario/
    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "consulta" + File.separator;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    @Transactional
    public void salvarManga(Long id, String nomeImagem, byte[] imagem) {
        
        Manga consulta = mangaRepository.findById(id);

        try {

            String novoNomeImagem = salvarImagem(imagem, nomeImagem);
            consulta.setNomeImagem(novoNomeImagem);
            // excluir a imagem antiga (trabalho pra quem????)
        } catch (IOException e) {
            throw new ValidationException("imagem", e.toString());
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {

        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        //se n colocar a linha de baixo vai receber tudo inclisive videos
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada.");
        }

        // verificando o tamanho do arquivo - nao permitir maior que 10 megas
        if (imagem.length > (1024 * 1024 * 20))
            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
        String nomeArquivo = UUID.randomUUID()
                +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // alunos (melhorar :)
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        // criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;

    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    }
}