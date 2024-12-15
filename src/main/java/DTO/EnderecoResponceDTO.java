package DTO;


import model.Endereco;

public record EnderecoResponceDTO(
        long idEndereco,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String nomeMunicipio,
        String sigla
) {

    public static EnderecoResponceDTO valueOf(Endereco endereco){

        return new EnderecoResponceDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getMunicipio().getNome(),
                endereco.getMunicipio().getEstado().getSigla()
        );
    }
}
