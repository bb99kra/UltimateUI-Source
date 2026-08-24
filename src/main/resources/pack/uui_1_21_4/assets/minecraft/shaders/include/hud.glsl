#define X 1
#define Y 1
#define refRes vec2(1920.0, 1080.0)

bool is_hud(vec3 Position) {
    return (Position.y < -1000.0);
}

bool make_hud() {
    if (is_hud(Position)) {
        vec3 pos = Position + vec3(0.0, 15000.0, 0.0);
        pos.x *= -1.0;
        float offset = 0.0;
        if (Position.y < -20000.0) {
            if (Position.y < -40000.0) {
                pos.y += 20000.0;
                offset = 1.0 - (ScreenSize.y / 9.0 * 16.0) / ScreenSize.x;
            } else if (Position.y < -30000.0) {
                pos.y += 10000.0;
            } else {
                offset = -1.0 + (ScreenSize.y / 9.0 * 16.0) / ScreenSize.x;
            }
            pos.y += 10000.0;
            pos.x *= (ScreenSize.y / 9.0 * 16.0) / ScreenSize.x;
        }

        pos.xy /= refRes * vec2(X, Y) / 2.0;
        pos.x += offset;

        pos.z /= 1000000.0;
        gl_Position = vec4(pos, 1.0);
        return true;
    }
    return false;
}