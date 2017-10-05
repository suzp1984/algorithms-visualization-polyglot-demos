//
//  ViewController.swift
//  collision-ball-ios
//
//  Created by Jacob Su on 10/5/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var circles : [Circle]  = []
    let N = 10
    let R = 50
    
    var collisionView : CollisionBallView?
    
    weak var timer : Timer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        self.view.backgroundColor = UIColor.lightGray
        
        let view = CollisionBallView()
        view.backgroundColor = UIColor.lightGray
        view.frame = self.view.bounds
        view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        self.view.addSubview(view)
        collisionView = view
        
        let sceneWidth = UInt32(self.view.bounds.width)
        let sceneHeight = UInt32(self.view.bounds.height)
        
        for _ in 1...N {
            let x = Int(arc4random_uniform(sceneWidth - UInt32(2 * R)) + UInt32(R))
            let y = Int(arc4random_uniform(sceneHeight - UInt32(2*R)) + UInt32(R))
            let vx = Int(arc4random_uniform(UInt32(11))) - 5
            let vy = Int(arc4random_uniform(11)) - 5

            circles.append(Circle(x: x, y: y, r: R, vx: vx, vy: vy))
        }

        startTimer()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func startTimer() {
        timer = Timer.scheduledTimer(withTimeInterval: 1, repeats: true) { _ in
            self.run()
        }
    }

    func stopTimer() {
        timer?.invalidate()
    }
    
    deinit {
        stopTimer()
    }
    
    private func run() {
        guard let collisionView = collisionView else {
            return
        }
        
        collisionView.render(circles: self.circles)
//        DispatchQueue.main.async {
//
//        }
    
        for i in 0..<circles.count {
            circles[i].move(minx: 0, miny: 0, maxx: Int(collisionView.frame.width), maxy: Int(collisionView.frame.height))
        }
        
    }
}

