package com.asjorok.pg

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import java.io.Closeable

class RabbitMQConnection(factory: ConnectionFactory) : Closeable {

    private val connection: Connection = factory.newConnection()
    val channel: Channel = connection.createChannel()

    override fun close() {
        channel.close()
        connection.close()
    }

}

fun createTestConnection(): RabbitMQConnection {
    val factory = ConnectionFactory();
    factory.host  = "localhost"
    factory.port = 5672
    factory.username = "guest"
    factory.password = "guest"

    return RabbitMQConnection(factory)
}