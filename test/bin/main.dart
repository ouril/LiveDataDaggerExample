import 'package:test/test.dart' as test;
import 'dart:io';

main(List<String> arguments) {
  print('Hello world: ${test.calculate()}!');

  Platform.environment.keys.forEach(
      (key){
        print(key);
      }
  );

  Process.run('ls', ['-l']).then(
      (ProcessResult result){
        print(result.stdout);
        print("Exit code : ${result.exitCode}");
      }
  );

}
