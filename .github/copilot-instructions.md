# Copilot Instructions for jonwho.github.io

## Project Overview
Jekyll-based personal blog hosted on GitHub Pages, themed with Scribble. Built with Jekyll, SCSS (Bourbon framework), and CoffeeScript.

## Architecture & Key Directories
- `_posts/`: Blog posts in markdown with YAML frontmatter (format: `YYYY-MM-DD-title.md`)
- `_layouts/`: HTML templates (`index.html`, `page.html`, `post.html`)
- `_includes/`: Reusable HTML partials (header, footer, share, disqus, etc.)
- `_assets/`: Source files for styles (SCSS) and scripts (CoffeeScript)
- `stylesheets/`: Compiled CSS output (don't edit directly)
- `javascripts/`: Compiled JS output (don't edit directly)
- `_assets/bourbon/`: Bourbon SCSS mixins library (vendored, don't modify)

## Development Workflow
**Primary command:** `rake` (default task)
- Starts Jekyll server with auto-reload (`jekyll server -w`)
- Watches and compiles SCSS from `_assets/` to `stylesheets/`
- Watches and compiles CoffeeScript from `_assets/` to `javascripts/`
- All three processes run in parallel via spawned tasks

**Dependencies:** Requires Ruby 2.3.3, bundled via `github-pages` gem

## Content Conventions
**Blog posts** (`_posts/`):
```yaml
---
layout: post
title: Post Title
date: YYYY-MM-DD HH:MM:SS
disqus: y  # optional, enables comments
share: y   # optional, enables share buttons
---
```

**Code highlighting:** Use Jekyll's liquid tags:
```
{% highlight ruby %}
def example
  "code here"
end
{% endhighlight %}
```

## Styling Patterns
- SCSS source in `_assets/style.scss` imports Bourbon mixins
- Theme uses predefined color classes: `.colour-1`, `.colour-2`, `.colour-3`
- Utility classes: `.upcase`, `.capitalize`, `.downcase`, `.muted`, `.a-r`, `.a-c`
- Main content max-width: 600px (see footer styling)
- Background color variable: `$background: #f9f9f9`

## Configuration (`_config.yml`)
- Site metadata: title, description, signoff
- Navigation links defined in `links:` array with optional `external: true`
- Syntax highlighting via Rouge with kramdown markdown
- Permalink style: `pretty` (no `.html` extensions)

## Key Integration Points
- **GitHub Pages:** Uses `github-pages` gem for deployment compatibility
- **Disqus:** Configured via `disqus_shortname` in `_config.yml`, enabled per-post
- **Google Analytics:** Set `google_analytics` in config, injected via `_includes/ga.html`
- **Date formatting:** CoffeeScript transforms `data-time` attributes using `prettyDate()` function

## File Edit Guidelines
- Edit SCSS in `_assets/*.scss`, not compiled `stylesheets/*.css`
- Edit CoffeeScript in `_assets/*.coffee`, not compiled `javascripts/*.js`
- New posts must follow naming: `YYYY-MM-DD-slug.md` in `_posts/`
- Layout inheritance: posts → `_layouts/post.html` → includes partials
