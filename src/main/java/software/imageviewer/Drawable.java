package software.imageviewer;

public record Drawable(int width, int height) {
        public Drawable resize(int width, int height) {
            return requireScale(width, height) ? scale(width, height) : this;
        }

        private Drawable scale(int width, int height) {
            if (this.width / width > this.height / height)
                return new Drawable(width, this.height * width / this.width);
            else
                return new Drawable(this.width * height / this.height, height);
        }

        private boolean requireScale(int width, int height) {
            return this.width > width || this.height > height;
        }

        public Point center(int width, int height) {
            return new Point((width-this.width) >> 1, (height-this.height) >> 1);
        }

    public record Point(int x, int y) {
    }
}
