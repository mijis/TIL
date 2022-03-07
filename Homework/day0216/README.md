### TIL - 2022.02.13
# 전체 흐름에 대한 중요성과 DataInputStream과 DataOutpuStream에 대한 이해부족


멀티 채팅에서 데이터를 주고 받는 DataInputStream(이하 dis) DataOutputStream(이하 dos)의 정확한 사용에 대해 무지했었다. 

예를 들어, dis와 dos를 통해 메시지를 주고받았다면 dis를 nick을 전달하기 위해서 또 쓸 수 없다는 바보 같은 생각에 갖혀있었다.

dis와 dos는 한 가지 경로로 밖에 못쓰인다는 고착된 생각을 하고 있었기 때문이다. 

그래서 채팅창 클라이언트의 nick을 server로 가져올 때 Map을 써서 key에 nick을 넣고, value에 dos를 넣어 ChatHelper에 저장하려했다.

이렇게 하면 전반적인 코드를 전부 수정해야했는데 아무리 생각해도 그건 아닌 것 같았다. 

몇 시간 째 코드 전체의 그림을 머리 속에 그릴 생각을 하지 않고 단편적인 코드의 부분만 보고 고민하고 있다가, 

예전에 전체의 흐름을 파악하고 틀린 점을 찾아냈던 기억이 불현듯 떠올랐고, 다시 전체의 흐름을 분석했다.

역시나 전체의 흐름을 정확히 파악하지 못하고 있었으며, dis와 dos에 대한 개념을 잘못이해하고 있었다. 

__*dis와 dos는 데이터를 전달하는 통로로서 여러 개를 사용할 수 있다는 점을 간과했다는 사실이 무척이나 아쉬웠고,*__

__*단편적인 부분을 보고 있는 것보다 전반적인 흐름을 이해하는 것이 무척이나 중요하다는 것을 다시금 깨닫게 되는 과정이었다.*__

늘 전체의 흐름을 생각하고 기초를 잘못이해하고 있지 않은지에 대해 항상 경각심을 가져야겠다.



++ *추가*
서버 접속 버튼을 눌렀을 때 이름이 들어가야한다고 생각했었으나, 선생님의 코드를 보고 나니 dos와 dis가 있는 곳에서 이름을 쓰는 게 더 보기 좋은 코드인 것 같다.


