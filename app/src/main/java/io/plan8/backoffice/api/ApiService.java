package io.plan8.backoffice.api;

import java.util.HashMap;
import java.util.List;

import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.model.api.Attachment;
import io.plan8.backoffice.model.api.Auth;
import io.plan8.backoffice.model.api.Login;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.NotificationItem;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("1/auth/pin-code")
    Call<Login> getPinCode(@Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("1/auth/token")
    Call<Auth> getAuthIfo(@Field("code") String code, @Field("pinCode") String pinCode);

    @GET("1/users/me")
    Call<User> getMe(@Header("authorization") String auth);

    @Multipart
    @POST("1/upload")
    Call<List<Attachment>> postUpload(@Header("authorization") String auth, @Part MultipartBody.Part files);

    @FormUrlEncoded
    @PUT("1/users/me")
    Call<User> putMe(@Header("authorization") String auth, @FieldMap HashMap<String, String> putMeMap);

    @GET("1/teams/{id}/reservations")
    Call<List<Reservation>> getReservations(@Header("authorization") String auth,
                                            @Path("id") int teamId,
                                            @Query("after") String after,
                                            @Query("before") String before,
                                            @Query("worker") int workerId,
                                            @Query("take") int take,
                                            @Query("skip") int skip);

    @GET("1/teams/{id}/reservations")
    Call<List<Reservation>> getReservations(@Header("authorization") String auth,
                                            @Path("id") int teamId,
                                            @Query("after") String after,
                                            @Query("before") String before,
                                            @Query("take") int take,
                                            @Query("skip") int skip);

    @GET("1/reservations/{id}")
    Call<Reservation> getReservation(@Header("authorization") String auth, @Path("id") int reservationId);

    @FormUrlEncoded
    @PUT("1/reservations/{id}")
    Call<Reservation> putReservation(@Header("authorization") String auth, @Path("id") int reservationId, @FieldMap HashMap<String, String> putReservationMap);

    @GET("1/users/me/members")
    Call<List<Member>> getUserMembers(@Header("authorization") String auth);

    @GET("1/teams/{id}/members")
    Call<List<Member>> getCurrentTeamMemebers(@Header("authorization") String auth, @Path("id") int teamId);

    @FormUrlEncoded
    @POST("1/reservations/{id}/actions")
    Call<Action> createAction(@Header("authorization") String auth, @Path("id") int reservationId, @Field("text") String text);

    @FormUrlEncoded
    @POST("1/reservations/{id}/actions")
    Call<Action> createAction(@Header("authorization") String auth, @Path("id") int reservationId, @Field("attachment") Attachment attachment);

    @GET("1/reservations/{id}/activities")
    Call<List<Action>> getActions(@Header("authorization") String auth,
                                  @Path("id") int reservationId,
                                  @Query("take") int take,
                                  @Query("skip") int skip);

    @GET("1/teams/{id}/notification")
    Call<List<NotificationItem>> getNotifications(@Header("authorization") String auth, @Path("id") int teamId);
}
