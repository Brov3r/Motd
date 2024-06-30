package com.brov3r.motd;

import com.avrix.events.OnPlayerFullyConnectedEvent;
import com.avrix.utils.ChatUtils;
import zombie.core.raknet.UdpConnection;

import java.nio.ByteBuffer;

/**
 * Event Handling
 */
public class OnPlayerConnectFullyHandler extends OnPlayerFullyConnectedEvent {
    /**
     * Handles the event when a player fully connects to the server.
     *
     * @param byteBuffer The byte buffer containing the connection data.
     * @param udpConnection The UDP connection of the player.
     * @param username The player username.
     */
    @Override
    public void handleEvent(ByteBuffer byteBuffer, UdpConnection udpConnection, String username) {
        if (udpConnection == null) return;
        
        for (String line : Main.getInstance().getDefaultConfig().getStringList("motdText")) {
            ChatUtils.sendMessageToPlayer(udpConnection, formatLine(line, udpConnection.username));
        }
    }

    /**
     * Formats a line of the MOTD (Message of the Day) to include the player's name.
     *
     * @param line The line of the MOTD to format.
     * @param playerName The name of the player.
     * @return The formatted line.
     */
    public static String formatLine(String line, String playerName) {
        return line.replaceAll("<PLAYER>", playerName).replaceAll("<SPACE>", ChatUtils.SPACE_SYMBOL);
    }
}