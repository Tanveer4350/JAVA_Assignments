import java.util.*;

class Song {

    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }

    // Required for duplicate checking
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Song)) {
            return false;
        }

        Song other = (Song) obj;

        return title.equalsIgnoreCase(other.title)
                && artist.equalsIgnoreCase(other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                title.toLowerCase(),
                artist.toLowerCase()
        );
    }
}


public class MediaPlayerQueue {

    // LinkedList for playback queue
    private LinkedList<Song> playlist;

    public MediaPlayerQueue() {
        playlist = new LinkedList<>();
    }

    // Add song normally
    public void addSong(Song song) {
        playlist.addLast(song);
        System.out.println(song + " added to playlist.");
    }

    // Add priority song to the front
    public void addPrioritySong(Song song) {
        playlist.addFirst(song);
        System.out.println(song +
                " added to the front as priority.");
    }

    // Remove duplicate songs
    public void removeDuplicates() {

        HashSet<Song> uniqueSongs = new HashSet<>();
        Iterator<Song> iterator = playlist.iterator();

        while (iterator.hasNext()) {

            Song song = iterator.next();

            if (!uniqueSongs.add(song)) {
                iterator.remove();
            }
        }

        System.out.println("Duplicate songs removed.");
    }

    // Traverse playlist forward
    public void playForward() {

        System.out.println("\n--- Playlist Forward ---");

        ListIterator<Song> iterator =
                playlist.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Traverse playlist backward
    public void playBackward() {

        System.out.println("\n--- Playlist Backward ---");

        ListIterator<Song> iterator =
                playlist.listIterator(playlist.size());

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    public static void main(String[] args) {

        MediaPlayerQueue player =
                new MediaPlayerQueue();

        // Add songs
        player.addSong(
                new Song("Believer", "Imagine Dragons")
        );

        player.addSong(
                new Song("Shape of You", "Ed Sheeran")
        );

        // Add duplicate
        player.addSong(
                new Song("Believer", "Imagine Dragons")
        );

        // Add priority song
        player.addPrioritySong(
                new Song("Perfect", "Ed Sheeran")
        );

        // Remove duplicates
        player.removeDuplicates();

        // Traverse forward
        player.playForward();

        // Traverse backward
        player.playBackward();
    }
}