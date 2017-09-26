using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TwoPlayer : MonoBehaviour {

    public void changeToTwo(int changeScene)
    {
        SceneManager.LoadScene(changeScene);
    }
}
