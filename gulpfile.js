var gulp = require('gulp');
var jshint = require('gulp-jshint');
var browserSync = require('browser-sync');
var reload = browserSync.reload;
var uncss = require('gulp-uncss');
var minify = require('gulp-minify-css');
var uglify = require('gulp-uglify');
var useref = require('gulp-useref');
var gulpif = require('gulp-if');
var del = require('del');

/*
 * Checks if the HTML files actually use all the CSS styles
 * if not then only build a CSS file that has the classes used.
 */
gulp.task('uncss', function() {
  return gulp.src(['cssfiles'])
    .pipe(uncss({
      html: ['index.html', 'views/**/*.html']
    }))
    .pipe(gulp.dest('dist/css'));
});

/*
 * Minify CSS files to save on file size.
 */
gulp.task('minify', ['uncss'], function() {
  return gulp.src('dist/css/cssfile')
    .pipe(minify({compatibility: 'ie8'}))
    .pipe(gulp.dest('dist/css/cssfile')); // should just rename it the same name
                                          // so won't need to update references
});

/*
 * Linter that notifies in console if writing bad JavaScript
 * based off .jshintrc config file.
 */
gulp.task('lint', function() {
  return gulp.src('/scripts/*.js')
    .pipe(jshint('.jshintrc'))
    .pipe(jshint.reporter('jshint-stylish'));
});

/*
 * Loads app and reloads app whenever the files that are being watched
 * changes. Syncs to all browsers for faster testing.
 */
gulp.task('browser-sync', ['lint'], function () {
  browserSync.init({
    server: {
      baseDir: './'
    }
  });

  gulp.watch(['index.html', 'styles/**/*.css', 'scripts/**/*.js', 'views/**/*.html'], {cwd: './'}, reload);
});

/*
 * Same task as browser-sync except this time it serves
 * distribution version of the app.
 */
gulp.task('serve-dist', ['lint'], function() {
  browserSync.init({
    server: {
      baseDir: './dist/'
    }
  });

  // sync the browser when these files change
  gulp.watch(['index.html', 'styles/**/*.css', 'scripts/**/*.js', 'views/**/*.html'], {cwd: './'}, reload);
});

/*
 * Deletes the dist directory. Uses a callback so that other
 * tasks have to wait on this task to finish first before running.
 */
gulp.task('clean-dist', function(cb) {
  del(['dist'], cb);
});

/*
 * Builds the assets for index.html and had to do this
 * separate because of file structure.
 */
gulp.task('build-index', ['clean-dist'], function() {
  var assets = useref.assets();

  return gulp.src(['index.html'])
    .pipe(assets)
    .pipe(gulpif('cssfiles', minify()))
    .pipe(assets.restore())
    .pipe(useref())
    .pipe(gulp.dest('dist'));
});

/*
 * Calls the other build tasks to run concurrently (only waiting on one task)
 * and builds views to dist.
 */
gulp.task('build', ['build-index'], function() {
  var assets = useref.assets();

  return gulp.src(['views/**/*.html'])
    .pipe(assets)
    .pipe(gulpif('cssfiles', minify()))
    .pipe(useref())
    .pipe(gulp.dest('dist/views'));
});

/*
 * Default task.
 */
gulp.task('default', ['browser-sync']);
