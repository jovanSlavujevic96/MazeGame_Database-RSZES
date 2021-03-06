package com.example.sql_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sql_client.game_pkg.Player;
import com.example.sql_client.game_pkg.activities_pkg.GameMenu;
import com.example.sql_client.pop_up.ActivityInterface;
import com.example.sql_client.pop_up.PopUpHandler;
import com.example.sql_client.tcp_socket_pkg.ClientSocket;
import com.example.sql_client.tcp_socket_pkg.activites_pkg.SignInActivity;
import com.example.sql_client.tcp_socket_pkg.activites_pkg.SignUpActivity;

public class MainActivity extends ActivityInterface
{
    private TextView signIn;
    private Button signUp, goToGameMenu;
    private ImageButton connectToSRV, signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientSocket.setActivityInterface(this);

        signIn = findViewById(R.id.sigin);
        signUp = findViewById(R.id.signup);
        goToGameMenu = findViewById(R.id.play_offline);
        connectToSRV = findViewById(R.id.reconnectSRV);
        signOut = findViewById(R.id.signOutBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!ClientSocket.isConnected() ) {
                    PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error message...", "You can't sign in.\nYou are not connected to server!", null);
                    return;
                }

                if(Player.getOffline() ) {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    return;
                }
                PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error message...", "You can't sign up.\nYou are already signed in!", null);
            }
        } );

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!ClientSocket.isConnected() ) {
                    PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error message...", "You can't sign in.\nYou are not connected to server!", null);
                    return;
                }

                if(Player.getOffline() ) {
                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intent);
                    return;
                }
                PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error message...", "You can't sign in.\nYou are already signed in!", null);
            }
        } );

        goToGameMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(! (ClientSocket.isConnected() && !Player.getOffline() ) ){
                    Player.setOffline(true);
                }
                Intent intent = new Intent(MainActivity.this, GameMenu.class);
                startActivity(intent);
            }
        } );

        connectToSRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ClientSocket.isConnected() )
                {
                    ClientSocket.ConnectWithServer();
                    if(ClientSocket.isConnected() && !Player.getOffline() )
                    {
                        Player.resetPlayersInfO();
                    }
                    return;
                }
                PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error...", "You are already connected to the server", null);
            }
        } );

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClientSocket.isConnected() )
                {
                    ClientSocket.DisconnectWithServer();
                    if(!ClientSocket.isConnected() && !Player.getOffline() )
                    {
                        Player.resetPlayersInfO();
                    }
                    return;
                }
                PopUpHandler.PopUp(ActivityInterface.current_context, -1, "Error...", "You are already signed out/disconnected", null);
            }
        } );

    }
}