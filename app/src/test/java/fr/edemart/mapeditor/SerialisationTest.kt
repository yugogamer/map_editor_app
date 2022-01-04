package fr.edemart.mapeditor

import fr.edemart.mapeditor.entity.Point
import fr.edemart.mapeditor.entity.Type
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class SerialisationTest {
    @Test
    fun deserializeStringToPoint() {
        val textToSerialize = """
            {
                "name": "Zone 1",
                "type": "TARGET",
                "latitude": 0.0,
                "longitude": 0.0
            }
        """.trimIndent()

        val expected = Point(
            name = "Zone 1",
            type = Type.TARGET,
            latitude = 0.0f,
            longitude = 0.0f
        )

        val serialize = Json.decodeFromString<Point>(textToSerialize)


        Assert.assertEquals(expected, serialize)
    }

    @Test
    fun deserializeStringToPointWithoutOptional() {
        val textToSerialize = """
            {
                "latitude": 0.0,
                "longitude": 0.0
            }
        """.trimIndent()

        val expected = Point(
            latitude = 0.0f,
            longitude = 0.0f
        )

        val serialize = Json.decodeFromString<Point>(textToSerialize)


        Assert.assertEquals(expected, serialize)
    }


    @Test
    fun serializeStringToPoint() {
        val expected = "{\"name\":\"Zone 1\",\"type\":\"TARGET\",\"latitude\":0.0,\"longitude\":0.0}"

        val toDeserialize = Point(
            name = "Zone 1",
            type = Type.TARGET,
            latitude = 0.0f,
            longitude = 0.0f
        )

        val deSerialize = Json.encodeToString(toDeserialize)
        println(deSerialize);


        Assert.assertEquals(expected, deSerialize)
    }
}