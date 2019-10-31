logstash-config.conf


input {
	jdbc {
		jdbc_driver_library => "<full_path_to_ojdbc8.jar>"
		jdbc_driver_class => "Java::oracle.jdbc.driver.OracleDriver"
		jdbc_connection_string => "jdbc:oracle:thin:@<host>:<port>/<service_name>"
		jdbc_user => "user"
		jdbc_password => "password"
		statement_filepath => "customer.sql" // sql file present in same directory
	}
}
 
output {
	elasticsearch {
		hosts => "localhost:9200"
		index => "customer"
		document_id => "%{id}"
	}
}


elasticsearch.yml 


path.data:
path.logs

network.bind_host: <local_ip>
discovery.type: single-node
