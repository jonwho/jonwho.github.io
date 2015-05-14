var gulp = require('gulp');
var jshint = require('gulp-jshint');
var browserSync = require('browser-sync');
var reload = browserSync.reload;

gulp.task('lint', function() {
  return gulp.src('/scripts/*.js')
    .pipe(jshint('.jshintrc'))
    .pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('browser-sync', ['lint'], function () {
  browserSync.init({
    server: {
      baseDir: './'
    }
  });

  gulp.watch(['index.html', 'styles/**/*.css', 'scripts/**/*.js', 'views/**/*.html'], {cwd: './'}, reload);
});

gulp.task('default', ['browser-sync']);