//This shader is used to outline objects with optional fading
#version 120

//Determines whether this fragment shader should enable debug features or not
uniform bool debug;

//The sampler used to sample from when taking samples from the texture
uniform sampler2D diffuseSampler;

//The intensity which is the alpha value of the faded fragment
uniform float fadeIntensity;

//The radius of which the samples should be taken from
uniform int sampleRadius;
//The maximum radius the sample can go
uniform int maxSampleRadius;

//The base color of which the current and the faded fragments derive from
uniform vec3 baseColor;
//The color that replaces the fragments of the object to outline
uniform vec3 objectColor;

//The size of a texel on the texture
uniform vec2 texelSize;

void main(void) {
    //Value that defines the alpha of the result fragment color
    //Also used to determine the alpha value for the replacement fragment color
    //If debug is enabled all objects to outline are visible and linear colored 
    float alpha = debug ? 1 : 0;

    //Sample actual color this fragment shader called for
    vec4 color = texture2D(diffuseSampler, gl_TexCoord[0].st);

    //Fragment has alpha not zero and therefore is an object to outline but not a fragment to sample
    if (color.a != 0) {
        //Set color to objects color
        gl_FragColor = vec4(objectColor, alpha);
    } else {
        //The final color derived from edge fragment of the object
        vec3 finalColor = vec3(0, 0, 0);
        //True if the fragment color has been determined
        bool foundColor = false;

        //Determine objects color
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                //Sample color with vector sample x and y added
                //IMPORTANT: multiply coordinates by texelsize in order to get the right coordinates
                vec4 sampleColor = texture2D(diffuseSampler, gl_TexCoord[0].st + vec2(x * texelSize.x, y * texelSize.y));

                //Found objects color
                if (sampleColor.a == 1) {
                    //Set variables and exit loop
                    finalColor = sampleColor.rgb;
                    foundColor = true;

                    break;
                }
            }

            //Found color, exit loop
            if (foundColor) {
                break;
            }
        }

        //Sample all fragments in sample radius
        for (int x = -sampleRadius; x <= sampleRadius; x++) {
            for (int y = -sampleRadius; y <= sampleRadius; y++) {
                //Sample color with vector sample x and y added
                //IMPORTANT: multiply coordinates by texelsize in order to get the right coordinates
                vec4 sampleColor = texture2D(diffuseSampler, gl_TexCoord[0].st + vec2(x * texelSize.x, y * texelSize.y));

                //When a fragment already has alpha then distance can be measured and added to alpha
                if (sampleColor.a != 0) {
                    //Adds inverted distance to alpha for fading more distant fragments
                    alpha += (max(0, (maxSampleRadius - sqrt(x * x + y * y)) / 2.0F)) * fadeIntensity;
                }
            }
        }

        //Sets fragment color with alpha divided by fade intensity
        gl_FragColor = vec4(finalColor, alpha);
    }
}