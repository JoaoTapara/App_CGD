package com.example.app_cgd;

import com.example.app_cgd.DTO.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    String BASE_URL = "http://philadelpho.ddns.net:100/api_cgd/";

    @GET("buscar_usuario.php")
    Call<Usuario> logar(
            @Query("email")String email,
            @Query("senha")String senha
    );

    @FormUrlEncoded
    @POST("cadastro_usuario.php")
    Call<Usuario> cadastrar (
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("senha") String senha,
            @Field("cpf") String cpf,
            @Field("telefone_gestante") String telefone_gestante
    );


}
