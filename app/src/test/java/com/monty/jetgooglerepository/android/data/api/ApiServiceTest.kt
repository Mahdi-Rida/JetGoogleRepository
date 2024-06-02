package com.monty.jetgooglerepository.android.data.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var service: ApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getSearchedRepository_sendRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getSearchedRepositories(
                query = "google",
                page = 1,
                perPage = 10
            )
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/google/repos?page=1&per_page=10&sort=created_at")
        }
    }

    @Test
    fun getSearchedRepository_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getSearchedRepositories(
                query = "google",
                page = 1,
                perPage = 10
            )
            val repositoryList = responseBody.body()!!
            assertThat(repositoryList.size).isEqualTo(10)
        }
    }

    @Test
    fun getSearchedRepository_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getSearchedRepositories(
                query = "google",
                page = 1,
                perPage = 10
            )
            val repositoryList = responseBody.body()!!
            val repository = repositoryList[0]
            assertThat(repository.name).isEqualTo("jpegli")
        }
    }

}