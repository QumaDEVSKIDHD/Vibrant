#version 120
uniform float avgDivisor;
uniform sampler2D diffuseSampler;
uniform int sampleRadius;
uniform vec2 texelSize;
uniform vec3 s_color;

void main() {
    vec4 centerCol = texture2D(diffuseSampler, gl_TexCoord[0].st);
    if(centerCol.a != 0) {
        gl_FragColor = vec4(0, 0, 0, 0);
        return;
    }
    float colAvg = 0;
    for (int x = -sampleRadius; x <= sampleRadius; x++) {
        for(int y = -sampleRadius; y <= sampleRadius; y++) {
            vec4 currCol = texture2D(diffuseSampler, gl_TexCoord[0].st + vec2(x * texelSize.x, y * texelSize.y));
            if (currCol.a != 0) {
                colAvg += max(0, (8.0F - sqrt(x * x + y * y)) / 2.0F);
            }
        }
    }
    colAvg /= avgDivisor;
    gl_FragColor = vec4(s_color, colAvg);
}