package olech.damian.appka;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class board extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[10][10];
    private Integer[][] ships = new Integer[10][10];


    private boolean player1Turn = true;
    private int player1Points;
    private int player1SmallPoints;
    private int player2Points;
    private int player2SmallPoints;
    private boolean hit;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        player1SmallPoints = 0;
        player2SmallPoints = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = 0;
            }
        }
        makeFour();
        boolean d = false;
        while (d == false) {
            d = makeThree();
        }
        d = false;
        while (d == false) {
            d = makeThree();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }


        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        hit = false;
        String id = v.getResources().getResourceEntryName(v.getId());
        char[] chars;
        chars = id.toCharArray();
        int i = (int) chars[7] - 48;
        int j = (int) chars[8] - 48;


        if (player1Turn) {
            if (ships[i][j].equals(1)) {
                ((Button) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.hit_red));
                ((Button) v).setText(" ");
                player1SmallPoints++;
                hit = true;
            } else {
                ((Button) v).setText(" ");
                ((Button) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.wave));
                hit = false;
            }

        } else {
            if (ships[i][j] == 1) {
                ((Button) v).setText(" ");
                ((Button) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.hit_green));

                player2SmallPoints++;
                hit = true;
            } else {
                ((Button) v).setText(" ");
                ((Button) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.wave));
                hit = false;
            }
        }

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (player1SmallPoints + player2SmallPoints == 20) {
            draw();

        }
        if (hit == false) {
            player1Turn = !player1Turn;

        }
        changeTurn(player1Turn);

    }

    private boolean checkForWin() {
        if (player1SmallPoints > 10 || player2SmallPoints > 10) {
            return true;
        } else {
            return false;
        }

    }

    private void makeRandom() {
        Random generator = new Random();
        for (int r = 0; r < 21; r++) {
            int i = generator.nextInt(10);
            int j = generator.nextInt(10);
            ships[i][j] = 1;
        }
    }

    private void makeFour() {
        Random generator = new Random();
        int i = generator.nextInt(10);
        int j = generator.nextInt(10);
        int d = generator.nextInt(3);

        ships[i][j] = 1;

        if (d == 0) {
            if (i + 3 < 10) {
                ships[i + 1][j] = 1;
                ships[i + 2][j] = 1;
                ships[i + 3][j] = 1;
                if (j + 1 < 10) {
                    ships[i][j + 1] = 2;
                    ships[i + 1][j + 1] = 2;
                    ships[i + 2][j + 1] = 2;
                    ships[i + 3][j + 1] = 2;
                }
                if (i - 1 >= 0) {
                    ships[i - 1][j] = 2;
                }

                if (i + 4 < 10) {
                    ships[i + 4][j] = 2;
                }

                if (j - 1 >= 0) {
                    ships[i][j - 1] = 2;
                    ships[i + 1][j - 1] = 2;
                    ships[i + 2][j - 1] = 2;
                    ships[i + 3][j - 1] = 2;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    ships[i - 1][j - 1] = 2;
                }
                if (i - 1 >= 0 && j + 1 < 10) {
                    ships[i - 1][j + 1] = 2;
                }
                if (i + 4 < 10 && j - 1 >= 0) {
                    ships[i + 4][j - 1] = 2;
                }
                if (i + 4 < 10 && j + 1 < 10) {
                    ships[i + 4][j + 1] = 2;
                }

            } else {
                d++;
            }
        }
        if (d == 1) {
            if (j + 3 < 10) {
                ships[i][j + 1] = 1;
                ships[i][j + 2] = 1;
                ships[i][j + 3] = 1;
                if (i + 1 < 10) {
                    ships[i + 1][j] = 2;
                    ships[i + 1][j + 1] = 2;
                    ships[i + 1][j + 2] = 2;
                    ships[i + +1][j + 3] = 2;
                }
                if (j - 1 >= 0) {
                    ships[i][j - 1] = 2;
                }
                if (j + 4 < 10) {
                    ships[i][j + 4] = 2;
                }
                if (i - 1 >= 0) {
                    ships[i - 1][j] = 2;
                    ships[i - 1][j + 1] = 2;
                    ships[i - 1][j + 2] = 2;
                    ships[i - 1][j + 3] = 2;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    ships[i - 1][j - 1] = 2;
                }
                if (j - 1 >= 0 && i + 1 < 10) {
                    ships[i + 1][j - 1] = 2;
                }
                if (i - 1 >= 0 && j + 4 < 10) {
                    ships[i - 1][j + 4] = 2;
                }
                if (i + 1 < 10 && j + 4 < 10)
                    ships[i + 1][j + 4] = 2;


            } else {
                d++;
            }
        }
        if (d == 2) {
            if (j + 1 < 10 && i + 1 < 10) {
                ships[i][j + 1] = 1;
                ships[i + 1][j] = 1;
                ships[i + 1][j + 1] = 1;

                if (i + 2 < 10) {
                    ships[i + 2][j] = 2;
                    ships[i + 2][j + 1] = 2;
                }

                if (i + 2 < 10 && j + 2 < 10) {
                    ships[i + 2][j + 2] = 2;
                }
                if (i + 2 < 10 && j - 1 >= 0) {
                    ships[i + 2][j - 1] = 2;
                }

                if (j - 1 >= 0) {
                    ships[i + 1][j - 1] = 2;
                    ships[i][j - 1] = 2;
                }
                if (j + 2 < 10) {
                    ships[i + 1][j + 2] = 2;
                    ships[i][j + 2] = 2;
                }

                if (i - 1 >= 0) {
                    ships[i - 1][j + 1] = 2;
                    ships[i - 1][j] = 2;
                }

                if (i - 1 >= 0 && j + 2 < 10) {
                    ships[i - 1][j + 2] = 2;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    ships[i - 1][j - 1] = 2;
                }

            } else {
                makeFour();
            }
        }

    }

    private Boolean makeThree() {
        Random generator = new Random();
        int i = generator.nextInt(10);
        int j = generator.nextInt(10);
        int d = generator.nextInt(2);

        if (ships[i][j] == 1 || ships[i][j] == 2) {
            return false;
        }

        if (d == 0) {
            if (i + 2 < 10) {
                if (ships[i + 1][j] == 2 || ships[i + 2][j] == 2) {
                    return false;


                } else {
                    ships[i + 1][j] = 1;
                    ships[i + 2][j] = 1;
                    ships[i][j] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        ships[i - 1][j - 1] = 2;
                    }
                    if (i - 1 >= 0) {
                        ships[i - 1][j] = 2;
                    }
                    if (i - 1 >= 0 && j + 1 < 10) {

                        ships[i - 1][j + 1] = 2;
                    }
                    if (j - 1 >= 0) {
                        ships[i][j - 1] = 2;
                        ships[i + 1][j - 1] = 2;
                        ships[i + 2][j - 1] = 2;

                    }
                    if (j - 1 >= 0 && i + 3 < 10) {
                        ships[i + 3][j - 1] = 2;
                    }
                    if (i + 3 < 10) {
                        ships[i + 3][j] = 2;
                    }
                    if (j + 1 < 10 && i + 3 < 10) {
                        ships[i + 3][j + 1] = 2;
                    }
                    if (j + 1 < 10) {
                        ships[i][j + 1] = 2;
                        ships[i + 1][j + 1] = 2;
                        ships[i + 2][j + 1] = 2;

                    }

                    return true;
                }
            }
            return false;
        }
        if (d == 1) {
            if (j + 2 < 10) {
                if (ships[i][j + 1] == 2 || ships[i][j + 2] == 2) {
                    return false;

                } else {
                    ships[i][j] = 1;
                    ships[i][j + 1] = 1;
                    ships[i][j + 2] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        ships[i - 1][j - 1] = 2;
                    }
                    if (i - 1 >= 0) {
                        ships[i - 1][j] = 2;
                        ships[i - 1][j + 1] = 2;
                        ships[i - 1][j + 2] = 2;
                    }
                    if (i - 1 >= 0 && j + 3 < 10) {
                        ships[i - 1][j + 3] = 2;
                    }
                    if (j - 1 >= 0) {
                        ships[i][j - 1] = 2;
                    }
                    if (j + 3 < 10) {
                        ships[i][j + 3] = 2;
                    }
                    if (i + 1 < 10 && j - 1 >= 0) {
                        ships[i + 1][j - 1] = 2;
                    }

                    if (i + 1 < 10) {
                        ships[i + 1][j] = 2;
                        ships[i + 1][j + 1] = 2;
                        ships[i + 1][j + 2] = 2;
                    }
                    if (i + 1 < 10 && j + 3 < 10) {
                        ships[i + 1][j + 3] = 2;
                    }
                    return true;
                }
            }

            return false;
        }
        return false;
    }

    private Boolean makeTwo() {
        Random generator = new Random();
        int i = generator.nextInt(10);
        int j = generator.nextInt(10);
        int d = generator.nextInt(2);

        if (ships[i][j] == 1 || ships[i][j] == 2) {
            return false;
        }
        if (d == 0) {
            if (j + 1 < 10) {
                if (ships[i][j + 1] == 2) {
                    return false;
                } else {
                    ships[i][j] = 1;
                    ships[i][j + 1] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        ships[i - 1][j - 1] = 2;
                    }
                    if (i - 1 >= 0) {
                        ships[i - 1][j] = 2;
                        ships[i - 1][j + 1] = 2;
                    }
                    if (i - 1 >= 0 && j + 2 < 10) {

                        ships[i - 1][j + 2] = 2;
                    }
                    if (j + 2 < 10) {
                        ships[i][j + 2] = 2;
                    }//
                    if (j - 1 >= 0) {
                        ships[i][j - 1] = 2;
                    }
                    if (i + 1 < 10) {
                        ships[i + 1][j] = 2;
                        ships[i + 1][j + 1] = 2;
                    }
                    if (j - 1 >= 0 && i + 1 < 10) {
                        ships[i + 1][j - 1] = 2;
                    }
                    if (j + 2 < 10 && i + 1 < 10) {
                        ships[i + 1][j + 2] = 2;

                    }

                    return true;
                }

            }
            return false;
        }
        if (d == 1) {
            if (i + 1 < 10) {
                if (ships[i + 1][j] == 2) {
                    return false;
                } else {
                    ships[i][j] = 1;
                    ships[i + 1][j] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        ships[i - 1][j - 1] = 2;
                    }
                    if (i - 1 >= 0) {
                        ships[i - 1][j] = 2;
                    }
                    if (i - 1 >= 0 && j + 1 < 10) {

                        ships[i - 1][j + 1] = 2;
                    }
                    if (j - 1 >= 0) {
                        ships[i][j - 1] = 2;
                        ships[i + 1][j - 1] = 2;
                    }
                    if (j - 1 >= 0 && i + 2 < 10) {
                        ships[i + 2][j - 1] = 2;
                    }
                    if (i + 2 < 10) {
                        ships[i + 2][j] = 2;
                    }
                    if (j + 1 < 10 && i + 2 < 10) {
                        ships[i + 2][j + 1] = 2;
                    }
                    if (j + 1 < 10) {
                        ships[i][j + 1] = 2;
                        ships[i + 1][j + 1] = 2;
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private boolean makeOne() {
        Random generator = new Random();
        int i = generator.nextInt(10);
        int j = generator.nextInt(10);

        if (ships[i][j] == 1 || ships[i][j] == 2) {
            return false;
        } else {
            ships[i][j] = 1;
            if (i - 1 >= 0 && j - 1 >= 0) {
                ships[i - 1][j - 1] = 2;
            }
            if (j - 1 >= 0) {
                ships[i][j - 1] = 2;
            }
            if (i + 1 < 10 && j - 1 >= 0) {
                ships[i + 1][j - 1] = 2;
            }

            if (i - 1 >= 0) {
                ships[i - 1][j] = 2;
            }

            if (i - 1 >= 0 && j + 1 < 10) {
                ships[i - 1][j + 1] = 2;
            }

            if (j + 1 < 10) {
                ships[i][j + 1] = 2;
            }

            if (i + 1 < 10 && j + 1 < 10) {
                ships[i + 1][j + 1] = 2;
            }
            if (i + 1 < 10) {
                ships[i + 1][j] = 2;
            }

            return true;
        }

    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        player1Points++;
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.square));

                ships[i][j] = 0;
            }

        }
        player1SmallPoints = 0;
        player2SmallPoints = 0;
        player1Turn = true;
        makeFour();
        boolean d = false;
        while (d == false) {
            d = makeThree();
        }
        d = false;
        while (d == false) {
            d = makeThree();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeTwo();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
        d = false;
        while (d == false) {
            d = makeOne();
        }
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        changeTurn(player1Turn);
    }

    private void changeTurn(boolean turn){
        TextView textView = findViewById(R.id.showTurn);
        if(turn==true){
            textView.setTextColor(Color.parseColor("#cc0000"));
        }
        else{
            textView.setTextColor(Color.parseColor("#33cc33"));

        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
        outState.putInt("player1SmallPoints", player1SmallPoints);
        outState.putInt("player2SmallPoints", player2SmallPoints);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player2SmallPoints = savedInstanceState.getInt("player2SmallPoints");
        player1SmallPoints = savedInstanceState.getInt("player1SmallPoints");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }




}
