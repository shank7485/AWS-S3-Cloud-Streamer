package vlc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

@Path("/vlc")
public class URL {

	private static JFrame frame;

	private static EmbeddedMediaPlayerComponent mediaPlayerComponent;

	private String mediaURL;
	
		/*
		URL call format:
		http://localhost:8080/vlc/rest/vlc/parameters?URL=http://s3-us-west-1.amazonaws.com/shank7485/Music/Canon.mp3&code=start

		http://localhost:8080/WebApp/rest/category/parameters?folder=Movies
		http://localhost:8080/WebApp/rest/category/parameters?folder=Music
		http://localhost:8080/WebApp/rest/category/parameters?folder=Others
		*/

	@GET
	@Path("/parameters")
	public Response RespMsg(@QueryParam("URL") String URL, @QueryParam("code") String code) {

		String mediaURL = URL;
		String command = code;
		command = command.trim();

		if (command.equals("start")) {
			System.out.println("Started");
			mediaStart(mediaURL);
		}

		else if (command.equals("pause")) {
			System.out.println("Paused");
			mediaPlayerComponent.getMediaPlayer().pause();
		}

		else if (command.equals("resume")) {
			System.out.println("Resumed");
			mediaPlayerComponent.getMediaPlayer().play();
		}

		else if (command.equals("forward")) {
			System.out.println("Seeking");
			mediaPlayerComponent.getMediaPlayer().skip(3000);
		}

		else if (command.equals("rewind")) {
			System.out.println("Rewind");
			mediaPlayerComponent.getMediaPlayer().skip(-3000);
		}

		else if (command.equals("stop")) {
			System.out.println("Stopping");
			mediaPlayerComponent.getMediaPlayer().stop();
			frame.setVisible(false); 
			frame.dispose(); 
		}

		else {
			System.out.println("Nothing");
		}

		String output = "URL: " + URL + " Command: " + command;

		return Response.status(200).entity(output).build();

	}

	public static void mediaStart(final String mediaURL) {
		new NativeDiscovery().discover();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new NativeDiscovery().discover();

				frame = new JFrame("Media Streamer");
				frame.setBounds(100, 100, 600, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel contentPane = new JPanel();
				contentPane.setLayout(new BorderLayout());

				mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
				contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

				frame.setContentPane(contentPane);
				frame.setVisible(true);

				mediaPlayerComponent.getMediaPlayer().playMedia(mediaURL);
			}
		});

	}

}
