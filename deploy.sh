#!/bin/bash

CURRENT_SHA=$(git rev-parse HEAD)

shadow-cljs release app
cd dist
git add .
git commit -m "Deploy $CURRENT_SHA"
git push origin gh-pages
