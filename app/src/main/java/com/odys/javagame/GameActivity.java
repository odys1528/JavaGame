package com.odys.javagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button upButton;
    private Button downButton;
    private Button northButton;
    private Button neButton;
    private Button eastButton;
    private Button seButton;
    private Button southButton;
    private Button swButton;
    private Button westButton;
    private Button nwButton;
    private Button quitButton;

    private TextView description;

    private int loc;
    private Map<Integer, Location> locations;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        upButton = findViewById(R.id.up_button);
        upButton.setOnClickListener(this);
        downButton = findViewById(R.id.down_button);
        downButton.setOnClickListener(this);
        northButton = findViewById(R.id.north_button);
        northButton.setOnClickListener(this);
        neButton = findViewById(R.id.ne_button);
        neButton.setOnClickListener(this);
        eastButton = findViewById(R.id.east_button);
        eastButton.setOnClickListener(this);
        seButton = findViewById(R.id.se_button);
        seButton.setOnClickListener(this);
        southButton = findViewById(R.id.south_button);
        southButton.setOnClickListener(this);
        swButton = findViewById(R.id.sw_button);
        swButton.setOnClickListener(this);
        westButton = findViewById(R.id.west_button);
        westButton.setOnClickListener(this);
        nwButton = findViewById(R.id.nw_button);
        nwButton.setOnClickListener(this);
        quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this);

        description = findViewById(R.id.description);

        locations = null;
        loc = 64;
        try {
            locations = ReadFiles.readLocationInfo(getApplicationContext().getAssets().open("locations.txt"),
                    getApplicationContext().getAssets().open("directions.txt"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("nie działa");
        }

        if(locations == null) {
            location = new Location(0, "Sorry, something went wrong, so the game will terminate");
        } else {
            location = locations.get(loc);
        }

        buttonUpdate(location);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_button:
                loc = location.getExits().get("U");
                break;
            case R.id.down_button:
                loc = location.getExits().get("D");
                break;
            case R.id.north_button:
                loc = location.getExits().get("N");
                break;
            case R.id.ne_button:
                loc = location.getExits().get("NE");
                break;
            case R.id.east_button:
                loc = location.getExits().get("E");
                break;
            case R.id.se_button:
                loc = location.getExits().get("SE");
                break;
            case R.id.south_button:
                loc = location.getExits().get("S");
                break;
            case R.id.sw_button:
                loc = location.getExits().get("SW");
                break;
            case R.id.west_button:
                loc = location.getExits().get("W");
                break;
            case R.id.nw_button:
                loc = location.getExits().get("NW");
                break;
            case R.id.quit_button:
                loc=0;
                location = locations.get(loc);
                Toast.makeText(this, location.getDescription(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }
        location = locations.get(loc);
        buttonUpdate(location);
    }

    private void disableAllButtons() {
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(upButton, downButton, northButton,
                neButton, eastButton, seButton, southButton, swButton, westButton, nwButton));
        for(Button b: buttons) {
            b.setEnabled(false);
        }
    }

    private void buttonUpdate(Location location) {
        description.setText(location.getDescription());
        disableAllButtons();

        for(String direction: location.getExits().keySet()) {
            switch (direction) {
                case "U":
                    upButton.setEnabled(true);
                    break;
                case "D":
                    downButton.setEnabled(true);
                    break;
                case "N":
                    northButton.setEnabled(true);
                    break;
                case "S":
                    southButton.setEnabled(true);
                    break;
                case "E":
                    eastButton.setEnabled(true);
                    break;
                case "W":
                    westButton.setEnabled(true);
                    break;
                case "NE":
                    neButton.setEnabled(true);
                    break;
                case "NW":
                    nwButton.setEnabled(true);
                    break;
                case "SE":
                    seButton.setEnabled(true);
                    break;
                case "SW":
                    swButton.setEnabled(true);
                    break;
            }
        }
    }
}
