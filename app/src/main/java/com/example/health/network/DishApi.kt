import com.example.health.pojo.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DishApi {
    @GET("/api/dishes/{userId}")
    fun getDishesByUserId(@Path("userId") userId: Int): Call<Response>
}
