package DTO;


import model.Endereco;

public record EnderecoResponceDTO(
        long idEndereco,
        String cep,

        String logradouro,
        String complemento,
        String bairro,
        long idMunicipio
) {

    public static EnderecoResponceDTO valueOf(Endereco endereco){

        return new EnderecoResponceDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getMunicipio().getId()
        );
    }
}
