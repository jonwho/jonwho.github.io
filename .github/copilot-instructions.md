# Copilot Instructions for jonwho.github.io

## Project Overview
Simple static HTML landing page hosted on GitHub Pages. Single-page site with professional links.

## Architecture
**Single-file design:** All HTML and CSS in `index.html`
- Self-contained split-screen layout
- Left panel: Name and tagline
- Right panel: Professional links (GitHub, LinkedIn, Resume)
- Fully responsive with mobile breakpoint at 968px

## Files
- `index.html` - Main landing page (edit this for content changes)
- `resume.pdf` - PDF resume linked from the site
- `404.html` - GitHub Pages error page
- `favicon.ico` - Site icon

## Development Workflow
**Local preview:**
```bash
python3 -m http.server 4000
```
Then visit http://localhost:4000

**Deployment:** 
Push to `master` branch - GitHub Pages auto-deploys

## Styling Details
- Font: System font stack (-apple-system, BlinkMacSystemFont, etc.)
- Left panel: `#2d3748` background
- Right panel: White background
- Hover effects: Border color transitions and arrow animations
- Mobile: Switches to vertical stacking under 968px width

## Updating Content
**To change links:** Edit the `<ul class="links-list">` section in `index.html`
**To update bio/tagline:** Edit `.tagline` paragraph text
**To modify colors:** Update inline `<style>` section CSS variables
**To replace resume:** Replace `resume.pdf` file and push to GitHub
