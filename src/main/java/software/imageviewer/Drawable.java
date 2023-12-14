package software.imageviewer;

public record Drawable(int width, int height) {
        public Drawable resize(int width, int height) {
            return requireScale(width, height) ? scale(width, height) : this;
        }

        private Drawable scale(int width, int height) {
            return new Drawable(
                    requireVerticalScale(width, height) ? width : this.width * height / this.height,
                    requireVerticalScale(width, height) ? this.height * width / this.width : height
            );
        }

        private boolean requireVerticalScale(int width, int height) {
            return this.width > width;
        }

        private boolean requireScale(int width, int height) {
            return this.width > width || this.height > height;
        }

        public Point center(int width, int height) {
            return new Point((width-this.width)/2, (height-this.height)/2);
        }

        private double ratio() {
            return (double) width / height;
        }

        public record Point(int x, int y) {

        }
}
