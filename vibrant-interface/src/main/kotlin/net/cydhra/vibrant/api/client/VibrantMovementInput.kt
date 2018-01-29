package net.cydhra.vibrant.api.client

/**
 *
 */
interface VibrantMovementInput {

    /**
     * The speed at which the player is strafing. Postive numbers to the left and negative to the right.
     */
    var moveStrafe: Float
    var moveForward: Float
    var jump: Boolean
    var sneak: Boolean
}