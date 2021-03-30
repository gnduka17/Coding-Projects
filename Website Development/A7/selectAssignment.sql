-- Display albums that have the letters “on” somewhere in the album title. 
-- Sort results in alphabetical order by album title.
SELECT *
FROM albums
WHERE title like '%on%'
ORDER BY title ASC;

-- Display album title and artist name that have the letters “on” somewhere in the album title. 
-- Sort results in alphabetical order by album title.
SELECT albums.title, artists.name
FROM albums
JOIN artists
ON albums.artist_id = artists.artist_id
WHERE title like '%on%'
ORDER BY title ASC;

-- Display tracks that have AAC audio file format.
-- Only show track name (alias: track_name), composer, 
-- media type name (alias: media_type), and unit price columns.
SELECT tracks.name, tracks.composer, media_types.name, tracks.unit_price
FROM tracks
JOIN media_types
ON tracks.media_type_id = media_types.media_type_id
WHERE tracks.media_type_id = 5;

-- Display R&B/Soul and Jazz tracks that have a composer (not NULL)
-- Sort results in reverse-alphabetical order by track name
-- Only show track ID, track name (track_name), composer, milliseconds, and genre name (genre_name) columns.
SELECT tracks.track_id, tracks.name, tracks.composer, tracks.milliseconds, genres.name
FROM tracks
JOIN genres
ON tracks.genre_id = genres.genre_id
WHERE (tracks.genre_id = 2 OR tracks.genre_id = 14) AND tracks.composer IS NOT NULL
ORDER BY tracks.name DESC;

-- Display drama (genre) DVDs that won awards
-- Sort results by year of when the DVD won an award
-- Show dvd title, award, genre, label, and rating.
SELECT dvd_titles.title,dvd_titles.award, genres.genre, labels.label, ratings.rating
FROM dvd_titles
JOIN genres
ON dvd_titles.genre_id = genres.genre_id
JOIN labels
ON dvd_titles.label_id = labels.label_id
JOIN ratings
ON dvd_titles.rating_id = ratings.rating_id
WHERE dvd_titles.award IS NOT NULL AND dvd_titles.genre_id = 9
ORDER BY dvd_titles.award ASC;

-- Display DVDs made by Universal (a label) and have DTS sound
-- Show dvd title, sound, label, genre, and rating.
SELECT dvd_titles.title, sounds.sound, labels.label, genres.genre, ratings.rating
FROM dvd_titles
JOIN sounds
ON dvd_titles.sound_id = sounds.sound_id
JOIN labels
ON dvd_titles.label_id = labels.label_id
JOIN genres
ON dvd_titles.genre_id = genres.genre_id
JOIN ratings
ON dvd_titles.rating_id = ratings.rating_id
WHERE dvd_titles.label_id = 127 AND dvd_titles.sound_id = 4;

-- Display R-rated Sci-Fi DVDs that have a release date (not NULL). 
-- Order results from newest to oldest released DVD. 
-- Show dvd title, release date, rating, genre, sound, and label.

SELECT dvd_titles.title, dvd_titles.release_date,ratings.rating,genres.genre, sounds.sound, labels.label
FROM dvd_titles
JOIN sounds
ON dvd_titles.sound_id = sounds.sound_id
JOIN labels
ON dvd_titles.label_id = labels.label_id
JOIN genres
ON dvd_titles.genre_id = genres.genre_id
JOIN ratings
ON dvd_titles.rating_id = ratings.rating_id
WHERE dvd_titles.release_date IS NOT NULL AND dvd_titles.genre_id = 19 AND dvd_titles.rating_id = 7
ORDER BY dvd_titles.release_date DESC;




