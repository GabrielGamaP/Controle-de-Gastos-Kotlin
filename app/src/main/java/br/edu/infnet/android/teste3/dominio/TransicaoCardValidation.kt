package br.edu.infnet.android.teste3.dominio

import br.edu.infnet.android.teste3.excecao.InvalidNameException


class TransicaoCardValidation {

    companion object {

        const val NAME_TOO_SHORT_EXCEPTION = "A opção precisa ter ao menos 3 caracteres."

        fun validate(businessCard: TransicaoCard): Either<TransicaoCard?, Exception?> {
            return when (businessCard.nome.length) {
                0, 1, 2 -> Either.BusinessCardEither<TransicaoCard?, Exception?>(
                    null,
                    InvalidNameException(NAME_TOO_SHORT_EXCEPTION)
                )
                else -> Either.BusinessCardEither<TransicaoCard?, Exception?>(businessCard, null)
            }
        }
    }

}