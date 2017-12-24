package io.plan8.backoffice.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.model.api.Attachment;
import io.plan8.backoffice.model.api.Auth;
import io.plan8.backoffice.model.api.Login;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.ServerTime;
import io.plan8.backoffice.model.api.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<User> getMe();

    @Multipart
    @POST("1/upload")
    Call<List<Attachment>> postUpload(@Part MultipartBody.Part files);

    @FormUrlEncoded
    @PUT("1/users/me")
    Call<User> putMe(@FieldMap HashMap<String, String> putMeMap);

    @GET("1/reservations")
    Call<List<Reservation>> getReservations(@Query("after") String after,
                                            @Query("before") String before,
                                            @Query("worker") int userId,
                                            @Query("take") int take,
                                            @Query("skip") int skip);

    @GET("1/reservations/{id}")
    Call<Reservation> getReservation(@Path("id") int reservationId);

    @FormUrlEncoded
    @PUT("1/reservations/{id}")
    Call<Reservation> putReservation(@Path("id") int reservationId, @FieldMap HashMap<String, String> putReservationMap);

    @GET("1/members")
    Call<List<Member>> getUserMembers(@Query("user") int userId);

    @GET("1/members")
    Call<List<Member>> getMembers(@Query("team") int teamId);

    @POST("1/actions")
    Call<Action> addAction(@Body Action action);

    @GET("1/actions")
    Call<List<Action>> getActions(@Query("reservation") int reservationId,
                                  @Query("take") int take,
                                  @Query("skip") int skip);

    @GET("1/users/me/notifications")
    Call<List<Notification>> getNotifications(@Query("take") int take,
                                              @Query("skip") int skip);

    @GET("1/users/me/notifications/count")
    Call<ResponseBody> getNotificationCount(@Query("read") boolean read);

    @FormUrlEncoded
    @PUT("1/notifications/{id}")
    Call<Notification> readNotification(@Path("id") int notificationId, @FieldMap Map<String, Boolean> readMap);

    @FormUrlEncoded
    @PUT("1/users/me/notifications")
    Call<List<Notification>> readAllNotifications(@Field("read") boolean read);

    @GET("1/server-time-offset")
    Call<ServerTime> getServerTime(@Query("now") String now);
}