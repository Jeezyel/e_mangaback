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
    public EnderecoResponceDTO(Endereco endereco){

        this(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getMunicipio().getId()
        );

    }
}
