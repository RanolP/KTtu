package io.github.ranolp.waffle.structure.user

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import io.github.ranolp.waffle.packet.OutPacket
import io.github.ranolp.waffle.packet.PacketOutChat

interface User {
    val displayName: String
    val id: String
    val locale: String
    val icon: String

    suspend fun sendPacket(packet: OutPacket)

    fun toJson(): JsonObject {
        return jsonObject("id" to id, "display_name" to displayName, "icon" to icon)
    }

    suspend fun sendMessage(message: String, from: User = EmptyUser) {
        sendPacket(PacketOutChat(from, message))
    }
}