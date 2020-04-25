package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class DisplayEngine {
    companion object {
        internal lateinit var batch: SpriteBatch
        internal lateinit var img: Texture
        internal lateinit var shapeRenderer : ShapeRenderer
        internal lateinit var platform: Texture

        fun initialize() {
            batch = SpriteBatch()
            img = Texture("bck.png")
            platform = Texture("Platform.png")
            shapeRenderer = ShapeRenderer()

            if (MyGdxGame.scale == 2f) {
                val pixmap200 = Pixmap(Gdx.files.internal("bck.png"))
                val pixmap100 = Pixmap(1600, 800, pixmap200.format)
                pixmap100.drawPixmap(pixmap200,
                        0, 0, pixmap200.width, pixmap200.height,
                        0, 0, pixmap100.width, pixmap100.height
                )
                img = Texture(pixmap100)

                val pixmap2000 = Pixmap(Gdx.files.internal("Platform.png"))
                val pixmap1000 = Pixmap(250, 250, pixmap2000.format)
                pixmap1000.drawPixmap(pixmap2000,
                        0, 0, pixmap2000.width, pixmap2000.height,
                        0, 0, pixmap1000.width, pixmap1000.height
                )
                platform = Texture(pixmap1000)
            }
        }

        fun draw() {
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
            batch.begin()
            batch.draw(img, 0f, 0f, Gdx.graphics.getWidth().toFloat(), Gdx.graphics.getHeight().toFloat());
            batch.draw(platform,
                    GameEngine.gamePlatform.xPlatform * MyGdxGame.scale,
                    GameEngine.gamePlatform.yPlatform * MyGdxGame.scale,
                    500f * MyGdxGame.scale, 500f * MyGdxGame.scale
            )
            if (GameEngine.gameIsOver) {
                val font = BitmapFont()
                font.getData().setScale(5f * MyGdxGame.scale)
                font.draw(batch, "GAME OVER! SCORE: " + GameEngine.score.toString(), 1000f * MyGdxGame.scale, 800f * MyGdxGame.scale)
            }
            batch.end()
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
            shapeRenderer.circle(
                    GameEngine.gameBall.xBall * MyGdxGame.scale,
                    GameEngine.gameBall.yBall * MyGdxGame.scale,
                    GameEngine.gameBall.ballRadius * MyGdxGame.scale
            )
            shapeRenderer.setColor(Color.WHITE)
            shapeRenderer.end()
        }
    }
}