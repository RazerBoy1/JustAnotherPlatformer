package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meandi.justanotherplatformer.Utils.HighScoresController;
import com.meandi.justanotherplatformer.Utils.Storage;

public class InputHighScoreScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin10f;
    private final Skin skin2f;

    private final HighScoresController highScoresController;
    private final Storage storage;
    private final int score;

    public InputHighScoreScreen(final JustAnotherPlatformer jap, final Storage storage, final int score) {
        super(jap);
        stage = new Stage();

        skin10f = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        skin2f = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        skin10f.getFont("font").getData().setScale(10f);
        skin2f.getFont("font").getData().setScale(2f);

        this.storage = storage;
        this.score = score;
        this.highScoresController = storage.getHighScoresController();

        final TextField textField = new TextField("", skin10f);
        textField.setMaxLength(3);
        textField.setDisabled(true);
        textField.setAlignment(Align.center);

        TextButton A = new TextButton("A", skin2f);
        TextButton B = new TextButton("B", skin2f);
        TextButton C = new TextButton("C", skin2f);
        TextButton D = new TextButton("D", skin2f);
        TextButton E = new TextButton("E", skin2f);
        TextButton F = new TextButton("F", skin2f);
        TextButton G = new TextButton("G", skin2f);
        TextButton H = new TextButton("H", skin2f);
        TextButton I = new TextButton("I", skin2f);
        TextButton J = new TextButton("J", skin2f);
        TextButton L = new TextButton("L", skin2f);
        TextButton K = new TextButton("K", skin2f);
        TextButton M = new TextButton("M", skin2f);
        TextButton N = new TextButton("N", skin2f);
        TextButton O = new TextButton("O", skin2f);
        TextButton P = new TextButton("P", skin2f);
        TextButton Q = new TextButton("Q", skin2f);
        TextButton R = new TextButton("R", skin2f);
        TextButton S = new TextButton("S", skin2f);
        TextButton T = new TextButton("T", skin2f);
        TextButton U = new TextButton("U", skin2f);
        TextButton V = new TextButton("V", skin2f);
        TextButton W = new TextButton("W", skin2f);
        TextButton X = new TextButton("X", skin2f);
        TextButton Y = new TextButton("Y", skin2f);
        TextButton Z = new TextButton("Z", skin2f);
        TextButton questionMark = new TextButton("?", skin2f);
        TextButton exclamationMark = new TextButton("!", skin2f);
        TextButton delete = new TextButton("Delete", skin2f);
        TextButton confirm = new TextButton("Confirm", skin2f);

        A.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("A");

                return true;
            }
        });
        B.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("B");

                return true;
            }
        });
        C.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("C");

                return true;
            }
        });
        D.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("D");

                return true;
            }
        });
        E.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("E");

                return true;
            }
        });
        F.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("F");

                return true;
            }
        });
        G.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("G");

                return true;
            }
        });
        H.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("H");

                return true;
            }
        });
        I.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("I");

                return true;
            }
        });
        J.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("J");

                return true;
            }
        });
        K.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("K");

                return true;
            }
        });
        L.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("L");

                return true;
            }
        });
        M.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("M");

                return true;
            }
        });
        N.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("N");

                return true;
            }
        });
        O.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("O");

                return true;
            }
        });
        P.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("P");

                return true;
            }
        });
        Q.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("Q");

                return true;
            }
        });
        R.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("R");

                return true;
            }
        });
        S.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("S");

                return true;
            }
        });
        T.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("T");

                return true;
            }
        });
        U.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("U");

                return true;
            }
        });
        V.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("V");

                return true;
            }
        });
        W.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("W");

                return true;
            }
        });
        X.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("X");

                return true;
            }
        });
        Y.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("Y");

                return true;
            }
        });
        Z.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("Z");

                return true;
            }
        });
        questionMark.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("?");

                return true;
            }
        });
        exclamationMark.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textField.appendText("!");

                return true;
            }
        });
        delete.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String text = textField.getText();

                if (!text.equals(""))
                    textField.setText(text.substring(0, text.length() - 1));

                return true;
            }
        });
        confirm.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                highScoresController.addHighScore(textField.getText(), score);
                storage.save(highScoresController.getHighScores());
                jap.setScreen(new MainMenuScreen(jap));
                dispose();

                return true;
            }
        });

        Table t1 = new Table();
        t1.top();
        t1.setFillParent(true);

        t1.add(textField).width(1950).height(400).expandX().padTop(150);

        Table t2 = new Table();
        t2.center();
        t2.setFillParent(true);

        t2.row().padTop(500);

        t2.add(A).padLeft(20);
        t2.add(B).padLeft(20);
        t2.add(C).padLeft(20);
        t2.add(D).padLeft(20);
        t2.add(E).padLeft(20);

        t2.row().padTop(20);

        t2.add(F).padLeft(20);
        t2.add(G).padLeft(20);
        t2.add(H).padLeft(20);
        t2.add(I).padLeft(20);
        t2.add(J).padLeft(20);

        t2.row().padTop(20);

        t2.add(K).padLeft(20);
        t2.add(L).padLeft(20);
        t2.add(M).padLeft(20);
        t2.add(N).padLeft(20);
        t2.add(O).padLeft(20);

        t2.row().padTop(20);

        t2.add(P).padLeft(20);
        t2.add(Q).padLeft(20);
        t2.add(R).padLeft(20);
        t2.add(S).padLeft(20);
        t2.add(T).padLeft(20);

        t2.row().padTop(20);

        t2.add(U).padLeft(20);
        t2.add(V).padLeft(20);
        t2.add(W).padLeft(20);
        t2.add(X).padLeft(20);
        t2.add(Y).padLeft(20);

        t2.row().padTop(20);

        t2.add(Z).padLeft(20);
        t2.add(questionMark).padLeft(20);
        t2.add(exclamationMark).padLeft(20);
        t2.add(delete).padLeft(20);
        t2.add(confirm).padLeft(20);

        stage.addActor(t1);
        stage.addActor(t2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin10f.dispose();
        skin2f.dispose();
    }
}
