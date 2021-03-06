package pl.edu.agh.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Logger {
	protected static Logger logger;

	protected DateFormat dateFormat;

	protected Set<IMessageSerializer> registeredSerializers;

	public Logger() {
		init();
		this.registeredSerializers = new HashSet<IMessageSerializer>();
	}

	public Logger(Set<IMessageSerializer> registeredSerializers) {
		init();
		if (registeredSerializers == null) {
			throw new IllegalArgumentException("null argument");
		}
		this.registeredSerializers = registeredSerializers;
	}

	public static Logger getInstance() {
		if (logger == null)
			logger = new Logger();
		return logger;
	}

	public void registerSerializer(IMessageSerializer messageSerializer) {
		registeredSerializers.add(messageSerializer);
	}

	public void log(String message) {
		for (IMessageSerializer messageSerializer : registeredSerializers)
			messageSerializer.serializeMessage(dateFormat.format(new Date())
					+ ": " + message);
	}

	private void init() {
		dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	}

}
