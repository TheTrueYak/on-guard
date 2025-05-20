#version 120

uniform sampler2D DiffuseSampler;
uniform vec2 OutSize;
uniform float Time;
uniform float Fade;

#define EPSILON 0.000011

// Simple hash function to generate pseudo-random values from input
vec2 hash(vec2 p)
{
    p = vec2(dot(p, vec2(127.1, 311.7)), dot(p, vec2(269.5, 183.3)));
    return fract(sin(p) * 43758.5453);
}

// Simplex noise function
float snoise(vec2 v)
{
    vec2 i = floor(v);
    vec2 f = fract(v);
    vec2 u = f * f * (3.0 - 2.0 * f);

    return mix(
        mix(dot(hash(i + vec2(0.0, 0.0)), f - vec2(0.0, 0.0)),
            dot(hash(i + vec2(1.0, 0.0)), f - vec2(1.0, 0.0)), u.x),
        mix(dot(hash(i + vec2(0.0, 1.0)), f - vec2(0.0, 1.0)),
            dot(hash(i + vec2(1.0, 1.0)), f - vec2(1.0, 1.0)), u.x),
        u.y);
}

// Random function
float rand(vec2 co) {
   return fract(sin(dot(co.xy, vec2(12.9898, 78.233))) * 43758.5453);
}

// Fisheye effect function
vec2 fisheye(vec2 uv, float strength)
{
    vec2 center = vec2(0.5, 0.5); // Center of the screen
    vec2 dir = uv - center;       // Direction from center
    float dist = length(dir);      // Distance from the center
    float factor = 1.0 / (1.0 + dist * strength * Fade); // Fisheye effect factor
    return center + dir * factor; // Apply the fisheye distortion
}

void main() {
    vec2 uv = gl_FragCoord.xy / OutSize;
    float time = Time * 0.5;

    uv = fisheye(uv, 1.1);

    float noise = max(0.0, snoise(vec2(time, uv.y * 0.3)) - 0.3) * (1.0 / 0.7);

    noise += (snoise(vec2(time * 20.0, uv.y * 2.4)) - 0.5) * 0.5 * Fade;  // Increase glitch intensity

    float xpos = uv.x - noise * noise * 0.25;
    vec3 col = texture2D(DiffuseSampler, vec2(xpos, uv.y)).rgb;

    col = mix(col, vec3(rand(vec2(uv.y * time))), noise * 0.4);  // Increased randomness factor

    if (floor(mod(gl_FragCoord.y * 0.25, 2.0)) == 0.0) {
        col *= 1.0 - (0.2 * noise);  // Increased line glitch effect
    }

    float shift = noise * 0.1;  // Increased shift for more noticeable color glitch
    vec3 shiftedCol;
    shiftedCol.r = col.r;
    shiftedCol.g = texture2D(DiffuseSampler, vec2(xpos + shift, uv.y)).g;
    shiftedCol.b = texture2D(DiffuseSampler, vec2(xpos - shift, uv.y)).b;

    vec3 redTint = vec3(0.4, 0.2, 0.2);  // Reddish tint
    vec3 finalColor = shiftedCol; // Apply the tint

    gl_FragColor = vec4(finalColor, 1.0);
}