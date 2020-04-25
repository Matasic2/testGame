package com.mygdx.game


import com.badlogic.gdx.*


class MyGdxGame : ApplicationAdapter() {

    companion object {
        var frameCounter: Int = 0
        var scale: Float = 1f
        var appType: Application.ApplicationType = Application.ApplicationType.Android
    }

    override fun create() {
       DisplayEngine.initialize()
    }

    override fun render() {
        GameEngine.update()
        DisplayEngine.draw()

        if (appType == Application.ApplicationType.Desktop) {
            GameEngine.leftIsPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT)
            GameEngine.rightIsPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        }

        else if (appType == Application.ApplicationType.Android) {
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() < 1200 * scale) {
                    GameEngine.leftIsPressed = true
                    GameEngine.rightIsPressed = false
                } else if (Gdx.input.getX() > 1400 * scale) {
                    GameEngine.rightIsPressed = true
                    GameEngine.leftIsPressed = false
                } else {
                    GameEngine.rightIsPressed = false
                    GameEngine.leftIsPressed = false
                }
            } else {
                GameEngine.rightIsPressed = false
                GameEngine.leftIsPressed = false
            }
        }
        //println(frameCounter++)
    }
}