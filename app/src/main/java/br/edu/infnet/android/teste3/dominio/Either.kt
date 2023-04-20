package br.edu.infnet.android.teste3.dominio

import java.lang.Exception

sealed class Either<T, U>(val t: T?, val u: U?) {

    class BusinessCardEither<T, U>(
        val transicaoCard: TransicaoCard?,
        val exception: Exception?
    ) : Either<T, U>(transicaoCard as T, exception as U)


}