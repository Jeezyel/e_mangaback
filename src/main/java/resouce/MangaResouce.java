package resouce;

import aplication.Result;
import form.ConsultaImageForm;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.ClassificacaoIndicativa;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import service.FileService;
import service.MangaService;
import java.util.List;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;

@Path("/mangas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MangaResouce {

    private static final Logger LOG = Logger.getLogger(MangaResouce.class);

    @Inject
    MangaService mangaService;

    @Inject
    FileService fileService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("100") int size) {
        return Response.ok(mangaService.getAll(page, size)).build();
    }

    @GET
    @Path("/search")
    public Response search(@QueryParam("nome") String nome,
                           @QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("100") int size) {
        if (nome == null || nome.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O parâmetro 'nome' é obrigatório.")
                    .build();
        }
        return Response.ok(mangaService.search(page, size, nome)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(MangaDTO mangaDTO) {
        LOG.info("Inserindo um novo mangá.");
        try {
            MangaResponceDTO manga = mangaService.create(mangaDTO);
            return Response.status(Response.Status.CREATED).entity(manga).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar o mangá: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, MangaDTO mangaDTO) {
        try {
            MangaResponceDTO manga = mangaService.update(id, mangaDTO);
            return Response.ok(manga).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                           .entity(e.getMessage())
                           .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }        
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        try {
            mangaService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }

    @GET
    @Path("/classificacaoIndicativa")
    public Response getClassificacoesIndicativas() {
        return Response.ok(ClassificacaoIndicativa.values()).build();
    }


    @GET
    @Path("/count")
    public long count() {
        return mangaService.count();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            MangaResponceDTO manga = mangaService.findById(id);
            return Response.ok(manga).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ConsultaImageForm form) {
        LOG.info("nome imagem: "+form.getNomeImagem());

        fileService.salvarManga(form.getId(), form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        System.out.println(nomeImagem);
        Response.ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}